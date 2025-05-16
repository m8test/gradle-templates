local ToolA = require("com.example.tool.ToolA")
local ToolB = {}
function ToolB.methodB(console)
    ToolA.methodA(console)
    console:log("Method B in ToolB called.")
end
return ToolB