



# from algorithms.bfs import (
#     maze_search,
#     shortest_distance_from_all_buildings,
#     ladder_length
# )
#
# import unittest
#
#
# class TestMazeSearch(unittest.TestCase):
#
#     def test_maze_search(self):
#         grid_1 = [[1,0,1,1,1,1],[1,0,1,0,1,0],[1,0,1,0,1,1],[1,1,1,0,1,1]]
#         self.assertEqual(14, maze_search(grid_1))
#         grid_2 = [[1,0,0],[0,1,1],[0,1,1]]
#         self.assertEqual(-1, maze_search(grid_2))
#
#
#
# if __name__ == "__main__":
#     unittest.main()


import numpy as np
import random

print( random.randint(1,10) )

trace1 = np.zeros( (100,10) )
trace2 = np.zeros((100, 10))


for item in trace1:
    item[random.randint(0,9)] = 1

for item in trace2:
    item[random.randint(0,9)] = 1

# print(trace1);
# print(trace2);

def compare_trace(t1, t2):
    for i in range(len(t1)):
        item1 = t1[i]
        item2 = t2[i]
        for j in range(len(item1)):
            if(item1[j] != item2[j]):
                print(i)
                break

# g = compare_trace(trace1, trace2)


trace3 = [[0,0,1,0,0,0],
          [0,1,0,0,0,0],
          [0,0,1,0,0,0],
          [0,0,0,1,0,0],
          [0,0,0,0,1,0],
          [0,0,0,1,0,0],

          [1,0,0,0,0,0],
          [0,1,0,0,0,0],
          [0,0,1,0,0,0]]

trace4 = [[1,0,0,0,0,0],
          [0,1,0,0,0,0],
          [0,0,1,0,0,0],

          [0,0,1,0,0,0],
          [0,1,0,0,0,0],
          [0,0,1,0,0,0],
          [0,0,0,1,0,0],
          [0,0,0,0,1,0],
          [0,0,0,1,0,0]]

def compare_trace_1(t1, t2):
    for i in range(len(t1)):
        item1 = t1[i]
        item2 = t2[i]
        if(item1 == item2):
            print(i)


g1 = compare_trace_1(trace3, trace4)




