#!/usr/bin/python
# -*- coding: UTF-8 -*-


x = 10
real = 1.0
str = "2*3"
print eval(str)
print "--------------"
s = "this is test"
c = 'i'

print int(x)         #将x转换为一个整数  
print long(x )        #将x转换为一个长整数  
print float(x )               #将x转换到一个浮点数  
print complex(real )  #创建一个复数  
#print str(x )                 #将对象 x 转换为字符串  
print repr(x )                #将对象 x 转换为表达式字符串  
print eval(str )              #用来计算在字符串中的有效Python表达式,并返回一个对象  
print tuple(s )               #将序列 s 转换为一个元组  
print list(s )                #将序列 s 转换为一个列表  
print chr(x )                 #将一个整数转换为一个字符  
print unichr(x )              #将一个整数转换为Unicode字符  
print ord(c )                 #将一个字符转换为它的整数值  
print hex(x )                 #将一个整数转换为一个十六进制字符串  
print oct(x )                 #将一个整数转换为一个八进制字符串  