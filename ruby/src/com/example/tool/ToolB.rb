require 'com/example/tool/ToolA'
class ToolB
  def methodB(logger)
    toolA = ToolA.new
    toolA.methodA(logger)
    logger.info("Method B in ToolB called.")
  end
end
