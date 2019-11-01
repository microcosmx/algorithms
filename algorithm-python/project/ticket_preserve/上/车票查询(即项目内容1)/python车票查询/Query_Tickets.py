'''
Usage:
	Query_Ticket.py [agdtkz] <from> <to> <date>
[gdtkz]:
	a            全部
	d            动车
	g            高铁
	k            快速
	t            特快
	z            直达
<data>:
	year-month-day
'''

import requests
from datetime import datetime
from prettytable import PrettyTable
from sys import argv
import Station_Parse
from requests.packages.urllib3.exceptions import InsecureRequestWarning
# 忽视该警告
requests.packages.urllib3.disable_warnings(InsecureRequestWarning)

# 获取参数
ticket_option = argv[1]
from_station = argv[2]
to_station = argv[3]
date = argv[4]


# 数据处理+显示
class Trains_Demo():
	# headers = '车次 车站 时间 历时 商务/特等座 一等座 二等座 高级软卧 软卧 动卧 硬卧 软座 硬座 无座 其他'.split()
	headers = '车次 车站 时间 历时 商务/特等座 一等座 二等座 高级软卧 软卧 动卧 硬卧 软座 硬座 无座'.split()
	# 初始化
	def __init__(self, raw_trains, option):
		self.raw_trains = raw_trains
		self.option = option
	# 获取出发和到达站
	def get_from_to_station_name(self, data_list):
		self.from_station_name = data_list[6]
		self.to_station_name = data_list[7]
		self.from_to_station_name = Station_Parse.parse_station().disparse(self.from_station_name) + '-->' + Station_Parse.parse_station().disparse(self.to_station_name)
		return self.from_to_station_name
	# 获得出发和到达时间
	def get_start_arrive_time(self, data_list):
		self.start_arrive_time = data_list[8] + '-->' + data_list[9]
		return self.start_arrive_time
	# 解析trains数据(与headers依次对应)
	def parse_trains_data(self, data_list):
		return {
			'trips': data_list[3],
			'from_to_station_name': self.get_from_to_station_name(data_list),
			'start_arrive_time': self.get_start_arrive_time(data_list),
			'duration': data_list[10],
			'business_premier_seat': data_list[32] or '--',
			'first_class_seat': data_list[31] or '--',
			'second_class_seat': data_list[30] or '--',
			'senior_soft_sleep': data_list[21] or '--',
			'soft_sleep': data_list[23] or '--',
			'move_sleep': data_list[33] or '--',
			'hard_sleep': data_list[28] or '--',
			'soft_seat': data_list[24] or '--',
			'hard_seat': data_list[29] or '--',
			'no_seat': data_list[26] or '--',
			# 'others': data_list[34] or '--'
			}
	# 判断是否需要显示
	def need_show(self, data_list):
		trips = data_list[3]
		initial = trips[0].lower()
		if self.option == 'a':
			return trips
		else:
			return(initial in self.option)
	# 数据显示
	def show_trian_data(self):
		self.demo = PrettyTable()
		self.demo._set_field_names(self.headers)
		for self.train in self.raw_trains:
			self.data_list = self.train.split('|')
			if self.need_show(self.data_list):
				self.values_row = []
				self.parsed_train_data = self.parse_trains_data(self.data_list)
				self.values_row.append(self.parsed_train_data['trips'])
				self.values_row.append(self.parsed_train_data['from_to_station_name'])
				self.values_row.append(self.parsed_train_data['start_arrive_time'])
				self.values_row.append(self.parsed_train_data['duration'])
				self.values_row.append(self.parsed_train_data['business_premier_seat'])
				self.values_row.append(self.parsed_train_data['first_class_seat'])
				self.values_row.append(self.parsed_train_data['second_class_seat'])
				self.values_row.append(self.parsed_train_data['senior_soft_sleep'])
				self.values_row.append(self.parsed_train_data['soft_sleep'])
				self.values_row.append(self.parsed_train_data['move_sleep'])
				self.values_row.append(self.parsed_train_data['hard_sleep'])
				self.values_row.append(self.parsed_train_data['soft_seat'])
				self.values_row.append(self.parsed_train_data['hard_seat'])
				self.values_row.append(self.parsed_train_data['no_seat'])
				# self.values_row.append(self.parsed_train_data['others'])
				self.demo.add_row(self.values_row)
		print(self.demo)


# 车票查询
class Query_Ticket(object):
	# 请求地址的模板
	url_template = (
		'https://kyfw.12306.cn/otn/leftTicket/query{}?leftTicketDTO.'
		'train_date={}&'
		'leftTicketDTO.from_station={}&'
		'leftTicketDTO.to_station={}&'
		'purpose_codes=ADULT'
		)
	# 初始化
	def __init__(self):
		self.ticket_option = ticket_option
		self.from_station = Station_Parse.parse_station().parse(from_station)
		self.to_station = Station_Parse.parse_station().parse(to_station)
		self.date = date
		if self.from_station is None or self.to_station is None:
			print('请输入有效车站名...')
			exit()
		try:
			if datetime.strptime(self.date, '%Y-%m-%d') < datetime.now():
				raise ValueError
		except:
			print('请输入有效日期...')
			exit()
	# 获得请求地址
	def request_url1(self):
		return(self.url_template.format('A', self.date, self.from_station, self.to_station))
	def request_url2(self):
		return(self.url_template.format('Z', self.date, self.from_station, self.to_station))
	# 查询车票
	def query(self):
		self.headers = {'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3294.6 Safari/537.36'}
		self.res = requests.get(self.request_url1(), headers=self.headers, verify=False)
		try:
			self.trains = self.res.json()['data']['result']
		except:
			self.res = requests.get(self.request_url2(), headers=self.headers, verify=False)
			self.trains = self.res.json()['data']['result']
		Trains_Demo(self.trains, self.ticket_option).show_trian_data()


if __name__ == '__main__':
	Query_Ticket().query()