#!/usr/bin/python
# -*- coding: UTF-8 -*-

tup1 = ('physics', 'chemistry', 1997, 2000);
tup2 = (1, 2, 3, 4, 5, 6, 7 );

print "tup1[0]: ", tup1[0]
print "tup2[1:5]: ", tup2[1:5]


tup1 = (12, 34.56);
tup2 = ('abc', 'xyz');

# 以下修改元组元素操作是非法的。
# tup1[0] = 100;

# 创建一个新的元组
tup3 = tup1 + tup2;
print tup3;


tup = ('physics', 'chemistry', 1997, 2000);

print tup;
del tup;
print "After deleting tup : "
#print tup;



len((1, 2, 3))	#3	计算元素个数
(1, 2, 3) + (4, 5, 6)	#(1, 2, 3, 4, 5, 6)	连接
['Hi!'] * 4	#['Hi!', 'Hi!', 'Hi!', 'Hi!']	复制
3 in (1, 2, 3)	#True	元素是否存在
for x in (1, 2, 3): print x,





#任意无符号的对象，以逗号隔开，默认为元组，如下实例：
print 'abc', -4.24e93, 18+6.6j, 'xyz';
x, y = 1, 2;
print "Value of x , y : ", x,y;