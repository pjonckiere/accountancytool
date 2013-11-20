min idee is tvolgende: 
- checkn op teken en bedrag
- checkn in boekhouding of dat etwot die van de ene rekening afgaat, ook op de andere derbie komt
- teruggeven lijst van zaken die OK zijn 
- teruggeven lijst van zaken in boekhouding maar niet op rekening 
- teruggeven lijst van zaken op rekening maar niet in boekhouding
dedie moetn we ton manueel matchen (evt. met e slimme matcher maja drover)
en ton rerun
tot dat oal in orde is

- storten samennemen
- alle items met dezelfde datum checken (hebben niet noodzakelijk dezelfde volgorde in de lijst)

per item op de kbc lijst checken of datet in assist zit

~/workspace/accountancytool/src/main/java$

find . -name "*.java" -print | xargs javac -cp /home/pieter/Downloads/poi-3.9/poi-3.9-20121203.jar
java be.dencouter.accountancytool.main.App

java -cp .:/home/pieter/Downloads/poi-3.9/poi-3.9-20121203.jar be.dencouter.accountancytool.main.App
