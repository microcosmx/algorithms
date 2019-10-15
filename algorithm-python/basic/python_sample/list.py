#!/usr/bin/python
# -*- coding: UTF-8 -*-

list1 = ['physics', 'chemistry', 1997, 2000];
list2 = [1, 2, 3, 4, 5, 6, 7 ];

print "list1[0]: ", list1[0]
print "list2[1:5]: ", list2[1:5]



listx = ['physics', 'chemistry', 1997, 2000];
listx += ["sss"]
print listx

print "Value available at index 2 : "
print listx[2];
listx[2] = 2001;
print "New value available at index 2 : "
print listx[2];



list1 = ['physics', 'chemistry', 1997, 2000];

print list1;
del list1[2];
print "After deleting value at index 2 : "
print list1;



len([1, 2, 3])
[1, 2, 3] + [4, 5, 6]
['Hi!'] * 4
3 in [1, 2, 3]
for x in [1, 2, 3]: print x,



li = ["a", "mpilgrim", "foo", "b", "c", "b", "d", "d"]
print [elem for elem in li if len(elem) > 1]
#['mpilgrim', 'foo']  
print [elem for elem in li if elem != "b"]
#['a', 'mpilgrim', 'foo', 'c', 'd', 'd']  
print [elem for elem in li if li.count(elem) == 1]
#['a', 'mpilgrim', 'foo', 'c']  



theList = ['a','b','c']
if 'a' in theList:
    print 'a in the list'

if 'd' not in theList:
    print 'd is not in the list'



li = [1,2,3,4,5,6]

# 1.使用del删除对应下标的元素
del li[2]
# li = [1,2,4,5,6]

# 2.使用.pop()删除最后一个元素
li.pop()
# li = [1,2,4,5]

# 3.删除指定值的元素
li.remove(4)
# li = [1,2,5]

# 4.使用切片来删除
li = li[:-1]
# li = [1,2]
# !!!切忌使用这个方法，如果li被作为参数传入函数，
# 那么在函数内使用这种删除方法，将不会改变原list


li = [1,2,3,4,5,6]
def delete(li, index):
    li = li[:index] + li[index+1:]
delete(li, 3)
print li
# 会输出[1,2,3,4,5,6]


for item in []:
	print "-----------------"




#方法一:
a=[2,3,4,5]
b=[2,5,8]
tmp = [val for val in a if val in b]
print tmp
#[2, 5]

#方法二
print list(set(a).intersection(set(b)))

print list(set(a).union(set(b)))

print list(set(b).difference(set(a))) # b中有而a中没有的






#join:
binfo = ['lao','wang','python']
content = ",".join(binfo)
print content









