# 分析
from openpyxl import load_workbook
from pyecharts.charts import Bar
from pyecharts import options as opts
import random


wb = load_workbook('./results/北京.xlsx')
sheet = wb.active
main_price = []
second_price = []
for name, cell in zip(list(sheet.columns)[0], list(sheet.columns)[3]):
	try:
		name_ = name.value
		price = int(cell.value)
		main_price.append([name_, price])
	except:
		continue
for name, cell in zip(list(sheet.columns)[0], list(sheet.columns)[4]):
	try:
		name_ = name.value
		price = int(cell.value)
		second_price.append([name_, price])
	except:
		continue


def DrawBar(bar_name, price):
	# bar = Bar()
	# bar.add_xaxis(["衬衫", "毛衣", "领带", "裤子", "风衣", "高跟鞋", "袜子"])
	# bar.add_yaxis("商家A", [114, 55, 27, 101, 125, 27, 105])
	# bar.add_yaxis("商家B", [57, 134, 137, 129, 145, 60, 49])
	# bar.set_global_opts(title_opts=opts.TitleOpts(title="某商场销售情况"))
	# bar.render()

	bar = Bar()
	attrs = []
	values = []
	for p in price:
		attrs.append(p[0])
		values.append(p[1])
	# bar.add("房价(元/平方)", attrs, values, mark_point=["average", "min", "max"])
	# bar.render('Bar{}.html'.format(random.random()))

	bar.add_xaxis(attrs)
	bar.add_yaxis("house", values)
	bar.set_global_opts(title_opts=opts.TitleOpts(title="北京房价(元/平方)"))
	bar.render()



if __name__ == '__main__':
	DrawBar('北京房价(元/平方)', main_price)
	DrawBar('北京房价(万元/套起)', second_price)
