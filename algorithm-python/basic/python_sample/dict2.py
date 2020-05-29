

print(dict())

dict1 = {'user': 'runoob', 'num': [1, 2, 3]}

dict2 = dict1  # 浅拷贝: 引用对象
dict3 = dict1.copy()  # 浅拷贝：深拷贝父对象（一级目录），子对象（二级目录）不拷贝，还是引用

print(dict3)