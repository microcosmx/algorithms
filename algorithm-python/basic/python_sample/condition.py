#!/usr/bin/python
# -*- coding: UTF-8 -*-

# 例1：if 基本用法

flag = False
name = 'luren'
if name == 'python':         # 判断变量否为'python'
    flag = True          # 条件成立时设置标志为真
    print 'welcome boss'    # 并输出欢迎信息
else:
    print name              # 条件不成立时输出变量名称







num = 5     
if num == 3:            # 判断num的值
    print 'boss'        
elif num == 2:
    print 'user'
elif num == 1:
    print 'worker'
elif num < 0:           # 值小于零时输出
    print 'error'
else:
    print 'roadman'     # 条件均不成立时输出






num = 9
if num >= 0 and num <= 10:    # 判断值是否在0~10之间
    print 'hello'

num = 10
if num < 0 or num > 10:    # 判断值是否在小于0或大于10
    print 'hello'
else:
	print 'undefine'

num = 8
# 判断值是否在0~5或者10~15之间
if (num >= 0 and num <= 5) or (num >= 10 and num <= 15):    
    print 'hello'
else:
    print 'undefine'







var = 100 
 
if ( var  == 100 ) : print "变量 var 的值为100" 
 
print "Good bye!" 




actionMap = {
    "ignore": "filter",
    "related": "related_to",
    "find": "select"
}
keys = ["ignore", "related"]
for item in keys:
    if actionMap.has_key(item):
        #print "find action: " + item
        pass
    else:
        print item
        actionMap.remove(item)

print actionMap










