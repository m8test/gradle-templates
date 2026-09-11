<?php

namespace com\example\tool;
require_once 'com/example/tool/ToolA.php';

class ToolB
{
    public static function methodB($logger)
    {
        ToolA::methodA($logger);
        $logger->info("Method B in ToolB called.");
    }
}
