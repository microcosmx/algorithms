class Animal:
    def run(self):
        raise AttributeError('must be implemented')
 
 
class People(Animal):
    def run(self):
        print('human is walking')
 
class Pig(Animal):
    def run(self):
        print('pig is walking')
 
 
class Dog(Animal):
    def run(self):
        print('dog is running')
 
peo1=People()
pig1=Pig()
d1=Dog()
 
peo1.run()
pig1.run()
d1.run()