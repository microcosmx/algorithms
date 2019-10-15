#!/usr/bin/python
# -*- coding: UTF-8 -*-
 
dict = {'Name': 'Zara', 'Age': 7, 'Class': 'First'};
 
print ("dict['Name']: ", dict['Name']);
print "dict['Age']: ", dict['Age'];


dict = {'Name': 'Zara', 'Age': 7, 'Class': 'First'};
 
dict['Age'] = 8; # update existing entry
dict['School'] = "DPS School"; # Add new entry

print "dict['Age']: ", dict['Age'];
print "dict['School']: ", dict['School'];



dict = {'Name': 'Zara', 'Age': 7, 'Class': 'First'};
print "dict['Age']: ", dict['Age'];
#print "dict['School']: ", dict['School'];
 
del dict['Name']; # 删除键是'Name'的条目
print dict

dict.clear();     # 清空词典所有条目
print dict

del dict ;        # 删除词典


dict={"name":"python","english":33,"math":35}

print "##for in "
for i in dict:
        print "dict[%s]=" % i,dict[i]

print "##items"
for (k,v) in  dict.items():
        print "dict[%s]=" % k,v

print "##iteritems"
for k,v in dict.iteritems():
        print "dict[%s]=" % k,v
 


d={'site':'http://www.jb51.net','name':'jb51','is_good':'yes'}
#方法1：通过has_key
print d.has_key('site')
#方法2：通过in
print 'body' in d.keys()





headers = ["a", "mpilgrim", "foo", "b", "c", "b", "d", "d"]
print {elem:{"type": "string"} for elem in headers}






