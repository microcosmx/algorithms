#!/usr/bin/python
# -*- coding: UTF-8 -*-

import time # 引入time模块

# import inspect
# inspect.getmoduleinfo(time)

localtime = time.localtime(time.time())
print "本地时间为 :", localtime

ticks = time.time()
print "当前时间戳为:", ticks

localtime = time.asctime( time.localtime(time.time()) )
print "本地时间为 :", localtime

print time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()) 

print "-----------------"







from time import gmtime, strftime
print strftime("%a, %d %b %Y %H:%M:%S +0000", gmtime())

print time.strptime("30 Nov 00", "%d %b %y")



import datetime,time

now = time.strftime("%Y-%m-%d %H:%M:%S")
print now

now = datetime.datetime.now()
print now









import calendar

cal = calendar.month(2016, 1)
print "以下输出2016年1月份的日历:"
print cal;









