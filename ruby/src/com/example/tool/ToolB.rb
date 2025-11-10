require 'com/example/tool/ToolA'
java_import "com.m8test.script.core.api.console.Console"

class ToolB
  # @param console [Console]
  def methodB(console)
    toolA = ToolA.new
    toolA.methodA(console)
    console.log("Method B in ToolB called.")
  end
end