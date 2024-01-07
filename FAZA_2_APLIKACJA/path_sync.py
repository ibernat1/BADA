import sys
import os

path_jbdc=f">{os.path.join(sys.path[0], 'ojdbc11.jar')}</"
path_pom=os.path.join(sys.path[0], "SpringApplication/pom.xml")
read_pom=open(path_pom, "r").read()

print(read_pom)

pom_array=read_pom.split("systemPath")

print(pom_array)

print(pom_array[1])
pom_array[1]=path_jbdc

write_pom=open(path_pom, "w")

write_pom.write(f"{pom_array[0]}systemPath{pom_array[1]}systemPath{pom_array[2]}")



