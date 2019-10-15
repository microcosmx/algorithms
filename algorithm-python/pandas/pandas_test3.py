

import pandas as pd

import numpy as np

import matplotlib.pyplot as plt


rng = pd.date_range('1/1/2012', periods=100, freq='S')
print(rng)

ts = pd.Series(np.random.randint(0, 500, len(rng)), index=rng)
print(ts)

print(ts.resample('5Min'))
print(ts.resample('5Min').sum())



rng = pd.date_range('3/6/2012 00:00', periods=5, freq='D')
ts = pd.Series(np.random.randn(len(rng)), rng)
print(ts)



rng = pd.date_range('1/1/2012', periods=5, freq='M')
ts = pd.Series(np.random.randn(len(rng)), index=rng)
print(ts)



df = pd.DataFrame({"id":[1,2,3,4,5,6], "raw_grade":['a', 'b', 'b', 'a', 'a', 'e']})
df["grade"] = df["raw_grade"].astype("category")
print(df["grade"])

df["grade"].cat.categories = ["very good", "good", "very bad"]
print(df["grade"])






# plotting
ts = pd.Series(np.random.randn(1000), index=pd.date_range('1/1/2000', periods=1000))
ts = ts.cumsum()
ts.plot()
# plt.show()



df = pd.DataFrame(np.random.randn(1000, 4), index=ts.index,
                   columns=['A', 'B', 'C', 'D'])

df = df.cumsum()

plt.figure(); 
df.plot(); 
plt.legend(loc='best')
plt.show()




df.to_csv('foo.csv')
print(pd.read_csv('foo.csv'))

# df.to_excel('foo.xlsx', sheet_name='Sheet1')
# pd.read_excel('foo.xlsx', 'Sheet1', index_col=None, na_values=['NA'])















