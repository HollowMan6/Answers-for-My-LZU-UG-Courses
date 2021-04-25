#!/usr/bin/python3
import re
rexp = input("Please input your regular expression: ")
toMatch = re.compile(rexp)
matchStr = input("Please input your string: ")
result = toMatch.match(matchStr)
if result and result.group(0) == matchStr:
    print("yes")
else:
    print("no")