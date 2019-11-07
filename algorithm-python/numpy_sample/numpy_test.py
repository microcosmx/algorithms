

import numpy as np


print(np.random.rand(6,6))
print(np.random.randint(2,6,(2,3))) #生成一个2x3整数数组,取值范围：[2,6)随机整数 
print(np.random.randint(0,2,(10,20)))
 
a = [1, 2, 3, 4]     	#
b = np.array(a)         	# array([1, 2, 3, 4])
type(b)                   	# <type 'numpy.ndarray'>
 
b.shape                   	# (4,)
b.argmax()               	# 3
b.max()                   	# 4
b.mean()                  	# 2.5
 
c = [[1, 2], [3, 4]]  	# 二维列表
d = np.array(c)         	# 二维numpy数组
d.shape                   	# (2, 2)
d.size                   	# 4
d.max(axis=0)            	# 找维度0，也就是最后一个维度上的最大值，array([3, 4])
d.max(axis=1)            	# 找维度1，也就是倒数第二个维度上的最大值，array([2, 4])
d.mean(axis=0)          	# 找维度0，也就是第一个维度上的均值，array([ 2.,  3.])
d.flatten()              	# 展开一个numpy数组为1维数组，array([1, 2, 3, 4])
np.ravel(c)               # 展开一个可以解析的结构为1维数组，array([1, 2, 3, 4])
 
# 3x3的浮点型2维数组，并且初始化所有元素值为1
e = np.ones((3, 3), dtype=np.float)
 
# 创建一个一维数组，元素值是把3重复4次，array([3, 3, 3, 3])
f = np.repeat(3, 4)
 
# 2x2x3的无符号8位整型3维数组，并且初始化所有元素值为0
g = np.zeros((2, 2, 3), dtype=np.uint8)
g.shape                    # (2, 2, 3)
h = g.astype(np.float)  # 用另一种类型表示
 
l = np.arange(10)      	# 类似range，array([0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
m = np.linspace(0, 6, 5)# 等差数列，0到6之间5个取值，array([ 0., 1.5, 3., 4.5, 6.])
 
p = np.array(
    [[1, 2, 3, 4],
     [5, 6, 7, 8]]
)
 
np.save('p.npy', p)     # 保存到文件
q = np.load('p.npy')    # 从文件读取







'''
array([[[ 0,  1,  2,  3],
        [ 4,  5,  6,  7],
        [ 8,  9, 10, 11]],

       [[12, 13, 14, 15],
        [16, 17, 18, 19],
        [20, 21, 22, 23]]])
'''
a = np.arange(24).reshape((2, 3, 4))
b = a[1][1][1]  # 17

'''
array([[ 8,  9, 10, 11],
       [20, 21, 22, 23]])
'''
c = a[:, 2, :]

''' 用:表示当前维度上所有下标
array([[ 1,  5,  9],
       [13, 17, 21]])
'''
d = a[:, :, 1]
 
''' 用...表示没有明确指出的维度
array([[ 1,  5,  9],
       [13, 17, 21]])
'''
e = a[..., 1]
 
'''
array([[[ 5,  6],
        [ 9, 10]],
 
       [[17, 18],
        [21, 22]]])
'''
f = a[:, 1:, 1:-1]
 
'''
平均分成3份
[array([0, 1, 2]), array([3, 4, 5]), array([6, 7, 8])]
'''
g = np.split(np.arange(9), 3)
 
'''
按照下标位置进行划分
[array([0, 1]), array([2, 3, 4, 5]), array([6, 7, 8])]
'''
h = np.split(np.arange(9), [2, -3])
 
l0 = np.arange(6).reshape((2, 3))
l1 = np.arange(6, 12).reshape((2, 3))
 
'''
vstack是指沿着纵轴拼接两个array，vertical
hstack是指沿着横轴拼接两个array，horizontal
更广义的拼接用concatenate实现，horizontal后的两句依次等效于vstack和hstack
stack不是拼接而是在输入array的基础上增加一个新的维度
'''
m = np.vstack((l0, l1))
p = np.hstack((l0, l1))
q = np.concatenate((l0, l1))
r = np.concatenate((l0, l1), axis=-1)
s = np.stack((l0, l1))
 
'''
按指定轴进行转置
array([[[ 0,  3],
        [ 6,  9]],
 
       [[ 1,  4],
        [ 7, 10]],
 
       [[ 2,  5],
        [ 8, 11]]])
'''
t = s.transpose((2, 0, 1))
 
'''
默认转置将维度倒序，对于2维就是横纵轴互换
array([[ 0,  4,  8],
       [ 1,  5,  9],
       [ 2,  6, 10],
       [ 3,  7, 11]])
'''
u = a[0].transpose()	# 或者u=a[0].T也是获得转置
 
'''
逆时针旋转90度，第二个参数是旋转次数
array([[ 3,  2,  1,  0],
       [ 7,  6,  5,  4],
       [11, 10,  9,  8]])
'''
v = np.rot90(u, 3)
 
'''
沿纵轴左右翻转
array([[ 8,  4,  0],
       [ 9,  5,  1],
       [10,  6,  2],
       [11,  7,  3]])
'''
w = np.fliplr(u)
 
'''
沿水平轴上下翻转
array([[ 3,  7, 11],
       [ 2,  6, 10],
       [ 1,  5,  9],
       [ 0,  4,  8]])
'''
x = np.flipud(u)
 
'''
按照一维顺序滚动位移
array([[11,  0,  4],
       [ 8,  1,  5],
       [ 9,  2,  6],
       [10,  3,  7]])
'''
y = np.roll(u, 1)
 
'''
按照指定轴滚动位移
array([[ 8,  0,  4],
       [ 9,  1,  5],
       [10,  2,  6],
       [11,  3,  7]])
'''
z = np.roll(u, 1, axis=1)









# 绝对值，1
a = np.abs(-1)
 
# sin函数，1.0
b = np.sin(np.pi/2)
 
# tanh逆函数，0.50000107157840523
c = np.arctanh(0.462118)
 
# e为底的指数函数，20.085536923187668
d = np.exp(3)
 
# 2的3次方，8
f = np.power(2, 3)
 
# 点积，1*3+2*4=11
g = np.dot([1, 2], [3, 4])
 
# 开方，5
h = np.sqrt(25)
 
# 求和，10
l = np.sum([1, 2, 3, 4])
 
# 平均值，5.5
m = np.mean([4, 5, 6, 7])
 
# 标准差，0.96824583655185426
p = np.std([1, 2, 3, 2, 1, 3, 2, 0])








a = np.array([3, 4])
np.linalg.norm(a)
 
b = np.array([
    [1, 2, 3],
    [4, 5, 6],
    [7, 8, 9]
])
c = np.array([1, 0, 1])
 
# 矩阵和向量之间的乘法
np.dot(b, c)            		# array([ 4, 10, 16])
np.dot(c, b.T)          		# array([ 4, 10, 16])
 
np.trace(b)             		# 求矩阵的迹，15
np.linalg.det(b)        		# 求矩阵的行列式值，0
np.linalg.matrix_rank(b)	# 求矩阵的秩，2，不满秩，因为行与行之间等差
 
d = np.array([
    [2, 1],
    [1, 2]
])
 
'''
对正定矩阵求本征值和本征向量
本征值为u，array([ 3.,  1.])
本征向量构成的二维array为v，
array([[ 0.70710678, -0.70710678],
       [ 0.70710678,  0.70710678]])
是沿着45°方向
eig()是一般情况的本征值分解，对于更常见的对称实数矩阵，
eigh()更快且更稳定，不过输出的值的顺序和eig()是相反的
'''
u, v = np.linalg.eig(d)
 
# Cholesky分解并重建
l = np.linalg.cholesky(d)
 
'''
array([[ 2.,  1.],
       [ 1.,  2.]])
'''
np.dot(l, l.T)
 
e = np.array([
    [1, 2],
    [3, 4]
])
 
# 对不镇定矩阵，进行SVD分解并重建
U, s, V = np.linalg.svd(e)
 
S = np.array([
    [s[0], 0],
    [0, s[1]]
])
 
'''
array([[ 1.,  2.],
       [ 3.,  4.]])
'''
np.dot(U, np.dot(S, V))




