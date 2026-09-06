import requests
from m8test_java.com.m8test.script.GlobalVariables import _logger

_logger.info(requests.get("https://www.baidu.com"))
