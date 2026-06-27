print("********GUESSING GAME**********")
import random
secret=random.randint(1,20)
while secret!=True:
     guess =int(input("Guess any number between(1-20):"))
  
     if guess==secret:
         print("Congrats!! you guessed right")
         break
     elif guess< secret:print("Too Low!!")
     
     else:print("Too High!!")
  