const path = require('path');
const fs = require('fs');
const WebpackObfuscator = require('webpack-obfuscator');

// --- 1. 读取全局变量和项目配置 (保持不变) ---
const globalVariablesPath = path.resolve(__dirname, 'build/json/GlobalVariables.json');
let reservedGlobalNames = [];
try {
    if (fs.existsSync(globalVariablesPath)) {
        const globalsFileContent = fs.readFileSync(globalVariablesPath, 'utf8');
        const globals = JSON.parse(globalsFileContent);
        reservedGlobalNames = globals.map(variable => variable.name);
        console.log('✅ Successfully loaded', reservedGlobalNames.length, 'global variables from JSON.');
    } else {
        console.warn('⚠️ Warning: GlobalVariables.json not found.');
    }
} catch (error) {
    console.error('❌ Error reading GlobalVariables.json:', error);
}

const projectConfigPath = path.resolve(__dirname, 'build/json/ProjectConfig.json');
let primaryScriptConfig = null;
let sideScriptsConfig = [];
try {
    if (fs.existsSync(projectConfigPath)) {
        const configFileContent = fs.readFileSync(projectConfigPath, 'utf8');
        const config = JSON.parse(configFileContent);
        if (config.entry) {
            const entryPath = config.entry.replace(/\\/g, '/');
            primaryScriptConfig = {
                name: entryPath,
                // Webpack 使用的相对路径
                path: './src/' + entryPath
            };
            console.log(`✅ Loaded entry point: ${primaryScriptConfig.path}`);
        } else {
            console.warn(`⚠️ Warning: ProjectConfig.json found, but 'entry' property is missing.`);
        }

        if (config.sides && Array.isArray(config.sides)) {
            sideScriptsConfig = config.sides.map(sideFile => {
                const sidePath = sideFile.replace(/\\/g, '/');
                return {
                    name: sidePath,
                    path: './src/' + sidePath
                };
            });
            console.log(`✅ Loaded ${sideScriptsConfig.length} side scripts.`);
        }

    } else {
        console.warn(`⚠️ Warning: ProjectConfig.json not found.`);
    }
} catch (error) {
    console.error(`❌ Error reading ProjectConfig.json: ${error}`);
}

// 递归查找所有JS文件的函数 (保持不变)
function findAllJsFiles(dir) {
    let results = [];
    const list = fs.readdirSync(dir);
    list.forEach(file => {
        const fullPath = path.join(dir, file);
        if (fs.statSync(fullPath).isDirectory()) {
            results = results.concat(findAllJsFiles(fullPath));
        } else if (file.endsWith('.js')) {
            results.push('./' + path.relative(__dirname, fullPath).replace(/\\/g, '/'));
        }
    });
    return results;
}

// --- 2. 主配置导出 ---
module.exports = (env, argv) => {
    const isProduction = argv.mode === 'production';

    const webpackAndModuleReservedNames = ['require', 'module', 'exports'];
    const allReservedNames = [...reservedGlobalNames, 'Packages', ...webpackAndModuleReservedNames];

    const plugins = [];
    if (isProduction) {
        console.log('✅ Applying JavaScript Obfuscator for production build.');
        plugins.push(
            new WebpackObfuscator({
                // ... 混淆器配置保持不变
                renameGlobals: false,
                renameProperties: false,
                reservedNames: allReservedNames,
                identifierNamesGenerator: 'hexadecimal',
                stringArray: true,
                rotateStringArray: true,
                transformObjectKeys: true,
                compact: true,
                deadCodeInjection: true,
                deadCodeInjectionThreshold: 0.4,
                target: 'node'
            }, [])
        );
    }

    const initScripts = findAllJsFiles(path.resolve(__dirname, 'init'));

    // --- 核心修改 1: 动态构建 entry 对象 ---
    const entryPoints = {};

    // 添加入口 (primary script)
    if (primaryScriptConfig) {
        entryPoints[primaryScriptConfig.name] = [...initScripts, primaryScriptConfig.path];
    }

    // 为每一个 side script 添加独立的入口
    sideScriptsConfig.forEach(side => {
        entryPoints[side.name] = [...initScripts, side.path];
    });

    console.log('--- Building the following independent entry points: ---');
    for (const key in entryPoints) {
        console.log(`  -> Entry '${key}.js' will be created from:`);
        entryPoints[key].forEach(file => console.log(`     - ${file}`));
    }
    console.log('----------------------------------------------------');

    return {
        mode: isProduction ? 'production' : 'development',
        // 使用上面构建的 entry 对象
        entry: entryPoints,
        output: {
            // --- 核心修改 2: 使用 [name] 占位符来生成动态文件名 ---
            filename: '[name]',
            path: path.resolve(__dirname, 'build/project/src'),
        },
        module: {
            rules: [
                {
                    test: /\.js$/,
                    exclude: /node_modules/,
                    use: {
                        loader: 'babel-loader',
                        options: {
                            presets: [
                                ["@babel/preset-env", {
                                    "useBuiltIns": "entry",
                                    "corejs": 3
                                }]
                            ]
                        }
                    }
                }
            ]
        },
        resolve: {
            modules: [
                path.resolve(__dirname, 'src'),
                path.resolve(__dirname, 'libs'),
                'node_modules'
            ]
        },
        plugins: plugins,
        devtool: isProduction ? false : 'eval-source-map'
    };
};