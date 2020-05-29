

print(dict())

dict1 = {'user': 'runoob', 'num': [1, 2, 3]}
print(dict1.items().copy())
# dict1  =  dict_items([('booster', 'gbtree'), ('objective', 'multi:softmax'), ('num_class', 3), ('gamma', 0.1), ('max_depth', 6), ('lambda', 2), ('subsample', 0.7), ('colsample_bytree', 0.7), ('min_child_weight', 3), ('slient', 1), ('eta', 0.1), ('seed', 1000), ('nthread', 4)])

dict2 = dict1  # 浅拷贝: 引用对象
dict3 = dict1.copy()  # 浅拷贝：深拷贝父对象（一级目录），子对象（二级目录）不拷贝，还是引用

print(dict3)