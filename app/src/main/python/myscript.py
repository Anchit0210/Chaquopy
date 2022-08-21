from nltk.chat.util import Chat

def main(inputdata):

  pairs = [
  ["Hello",["Hey pal"] ]
  ]

  chat = Chat(pairs)
  outputdata = chat.respond(inputdata)

  return ""+str(outputdata)