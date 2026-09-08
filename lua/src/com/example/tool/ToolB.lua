local ToolA = require("com.example.tool.ToolA")
local ToolB = {}
function ToolB.methodB(logger)
    ToolA.methodA(logger)
    logger:info("Method B in ToolB called.")
end
return ToolB
