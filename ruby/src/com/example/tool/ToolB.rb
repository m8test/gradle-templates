require 'com/example/tool/ToolA'

class ToolB
  def methodB(console)
    toolA = ToolA.new
    toolA.methodA(console)
    console.log("Method B in ToolB called.")
  end
end