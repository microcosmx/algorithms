


import pandas as pd

import numpy as np

import matplotlib.pyplot as plt




s = pd.Series([1,3,5,np.nan,6,8])
print(s)


dates = pd.date_range('20130101', periods=6)
print(dates)


df = pd.DataFrame(np.random.randn(6,4), index=dates, columns=list('ABCD'))
print(df)


df2 = pd.DataFrame({ 'A' : 1.,
                     'B' : pd.Timestamp('20130102'),
                     'C' : pd.Series(1,index=list(range(4)),dtype='float32'),
                     'D' : np.array([3] * 4,dtype='int32'),
                     'E' : pd.Categorical(["test","train","test","train"]),
                     'F' : 'foo' })
print(df2)
print(df2.dtypes)
# print(df2.<TAB>)


print(df.head())
print(df.tail(3))
print(df.index)
print(df.columns)
print(df.values)

print(df.describe())
print(df.T)

print(df.sort_index(axis=1, ascending=False))
print(df.sort_values(by='B'))



df['A']
df[0:3]
df['20130102':'20130104']
df.loc[dates[0]]
df.loc[:,['A','B']]
df.loc['20130102':'20130104',['A','B']]
df.loc['20130102',['A','B']]







print(df[df.A > 0])
print(df[df > 0])



df2 = df.copy()
df2['E'] = ['one', 'one','two','three','four','three']
print(df2)
print(df2[df2['E'].isin(['two','four'])])



# setting
s1 = pd.Series([1,2,3,4,5,6], index=pd.date_range('20130102', periods=6))
df['F'] = s1
print(df)

df.at[dates[0],'A'] = 0
print(df)

df.iat[0,1] = 0
print(df)

df.loc[:,'D'] = np.array([5] * len(df))
print(df)

df1 = df.reindex(index=dates[0:4], columns=list(df.columns) + ['E'])
df1.loc[dates[0]:dates[1],'E'] = 1
print(df1)

print(df1.dropna(how='any'))
print(df1.fillna(value=5))



print("---------------stats---------------")
print(df.mean())



print(df.apply(np.cumsum))
print(df.apply(lambda x: x.max() - x.min()))



s = pd.Series(np.random.randint(0, 7, size=10))
print(s)
print(s.value_counts())



s = pd.Series(['A', 'B', 'C', 'Aaba', 'Baca', np.nan, 'CABA', 'dog', 'cat'])
print(s.str.lower())















