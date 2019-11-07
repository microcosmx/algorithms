

import numpy as np
import pandas as pd
import matplotlib.pyplot as plt




ts = pd.Series(np.random.randn(1000), index=pd.date_range('1/1/2000', periods=1000))
ts = ts.cumsum()
ts.plot()



df = pd.DataFrame(np.random.randn(1000, 4), index=ts.index, columns=list('ABCD'))
df = df.cumsum()
# plt.figure(); 
df.plot();



print(range(10)[1])
df3 = pd.DataFrame(np.random.randn(1000, 2), columns=['B', 'C']).cumsum()
df3['A'] = pd.Series(list(range(len(df))))
df3.plot(x='A', y='B')



plt.figure();
print(df.iloc[5])
df.iloc[5].plot(kind='bar');



# all supported plots
# df = pd.DataFrame()
# key down "tab"
# df.plot.<TAB>
# df.plot.area     df.plot.barh     df.plot.density  df.plot.hist     df.plot.line     df.plot.scatter
# df.plot.bar      df.plot.box      df.plot.hexbin   df.plot.kde      df.plot.pie



plt.figure();
df.iloc[5].plot.bar(); 
plt.axhline(0, color='k')



df2 = pd.DataFrame(np.random.rand(10, 4), columns=['a', 'b', 'c', 'd'])
df2.plot.bar();
df2.plot.bar(stacked=True);
df2.plot.barh(stacked=True);



df4 = pd.DataFrame({'a': np.random.randn(1000) + 1, 'b': np.random.randn(1000),
                     'c': np.random.randn(1000) - 1}, columns=['a', 'b', 'c'])
# plt.figure();
df4.plot.hist(alpha=0.5)
df4.plot.hist(stacked=True, bins=20)
df4['a'].plot.hist(orientation='horizontal', cumulative=True)



df['A'].diff().hist()
df.diff().hist(color='k', alpha=0.5, bins=50)



df = pd.DataFrame(np.random.rand(10, 5), columns=['A', 'B', 'C', 'D', 'E'])
df.plot.box()
df.plot.box(vert=False, positions=[1, 4, 5, 6, 8])



df = pd.DataFrame(np.random.rand(10, 4), columns=['a', 'b', 'c', 'd'])
df.plot.area();
df.plot.area(stacked=False);



df = pd.DataFrame(np.random.rand(50, 4), columns=['a', 'b', 'c', 'd'])
df.plot.scatter(x='a', y='b');
df.plot.scatter(x='a', y='b', c='c', s=50);
df.plot.scatter(x='a', y='b', s=df['c']*200);



plt.figure();
series = pd.Series(3 * np.random.rand(4), index=['a', 'b', 'c', 'd'], name='series')
series.plot.pie(figsize=(6, 6))

plt.figure();
series.plot.pie(labels=['AA', 'BB', 'CC', 'DD'], colors=['r', 'g', 'b', 'c'],
                 autopct='%.2f', fontsize=20, figsize=(6, 6))



df = pd.DataFrame(3 * np.random.rand(4, 2), index=['a', 'b', 'c', 'd'], columns=['x', 'y'])
df.plot.pie(subplots=True, figsize=(8, 4))



plt.figure();
ser = pd.Series(np.random.randn(1000))
ser.plot.kde()



plt.figure();
from pandas.plotting import scatter_matrix
df = pd.DataFrame(np.random.randn(1000, 4), columns=['a', 'b', 'c', 'd'])
scatter_matrix(df, alpha=0.2, figsize=(6, 6), diagonal='kde')



plt.show()

