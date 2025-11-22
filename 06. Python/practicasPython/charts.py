import plotly.express as px

def prueba():
  i = 5
  val = [20, 50, 37, 18]
  while(i > 0):
    dec = input('Pulse Y para cambiar o N para mostrar').strip().upper()
    if (dec == "Y"):
      reemplazar = int(input("Introduzca un numero"))
      val[3] = reemplazar
    else:
      fig = px.pie(values = val,
      names = ['G1', 'G2', 'G3', 'G4'])
      fig.show()
    
    i-= 1
prueba()
