<?php

namespace com\example\tool;
require_once 'com/example/tool/ToolA.php';

class ToolB
{
    public static function methodB($console)
    {
        ToolA::methodA($console);
        $console->log("Method B in ToolB called.");
    }
}