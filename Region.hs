module Region ( Region, newR, foundR, linkR, tunelR, connectedR, linkedR, delayR, availableCapacityForR )
where

import City
import Quality
import Link
import Tunel
import Data.List (isInfixOf)

data Region = Reg [City] [Link] [Tunel] deriving (Show)

newR :: Region
newR = Reg [] [] []

foundR :: Region -> City -> Region -- agrega una nueva ciudad a la región
foundR region@(Reg cities links tunels) city = if ((not (elem city cities))&& not ((nameInRegion city region)||(pointInRegion city region))) then Reg (cities ++ [city]) links tunels else region
-- Si la ciudad ya existe en la región, la función no hace nada y devuelve la misma región

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR region@(Reg cities links tunels) c1 c2 qua | linkedR region c1 c2 = region
                                                 | elem c1 cities && elem c2 cities = error "no se pueden enlazar las ciudades"
                                                 |otherwise = Reg cities (links ++ [newL c1 c2 qua]) tunels 
-- Si el link ya existe en la región, la función no hace nada y devuelve la misma región

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
-- la lista de ciudades indica el camino ordenado de enlaces que debe tomar el túnel de inicio a fin 
tunelR region@(Reg cities links tuneles) cityList | connectedR region (head cityList) (last cityList) = region -- si ya existe un tunel entre los extremos, no se crea niguno nuevo y se devuelve la misma region
                                                | checksLinks region cityList = Reg cities links (tuneles ++ [newT (linksBetweenCities region cityList)])
                                                | otherwise = error "No todas las ciudades de la lista se encuentran enlazadas"
connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades están conectadas por un túnel
-- Dice si existe un túnel en la región que tenga estas ciudades por origen o destino
connectedR (Reg _ _ tunels) c1 c2 = foldr (\x acc -> connectsT c1 c2 x || acc) False tunels

linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades están enlazadas
-- Dice si existe un enlace en la región que entre estas dos ciudades
linkedR (Reg _ links _) c1 c2 = foldr (\x acc -> linksL c1 c2 x || acc) False links

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
delayR region c1 c2 = delayT (convertTunel (getSharedTunel region c1 c2))

availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
-- Teniendo en cuenta la capacidad que los túneles existentes ocupan
availableCapacityForR region c1 c2 = (capacityL link) - (usedCapacity region link)   
    where link = convertLink (getSharedLink region c1 c2)


-- Funciones extra

checkCities :: Region -> [City] -> Bool -- checkea si todas las ciudades de una lista existen en la region
checkCities (Reg regCities _ _) cityList = isInfixOf cityList regCities 
 
checksLinks :: Region -> [City] -> Bool -- checkea si existen links en la region entre todas las ciudades de la lista de forma ordenada
checksLinks _ [] = True
checksLinks _ [_] = True
checksLinks region (city1:city2:rest) = linkedR region city1 city2 && checksLinks region (city2:rest)

linksBetweenCities :: Region -> [City] -> [Link]
linksBetweenCities _ [] = []
linksBetweenCities _ [_] = []
linksBetweenCities region (city1:city2:rest) = if (availableCapacityForR region city1 city2) >= 1 then [convertLink (getSharedLink region city1 city2)] ++ (linksBetweenCities region (city2:rest)) 
                                                else error "No hay capacidad disponible para un tunel más"

getSharedLink :: Region -> City -> City -> Maybe Link
getSharedLink (Reg _ links _) c1 c2 = foldr (\x acc -> if linksL c1 c2 x then Just x else acc) Nothing links

getSharedTunel :: Region -> City -> City -> Maybe Tunel
getSharedTunel (Reg _ _ tunels) c1 c2 = foldr (\x acc -> if connectsT c1 c2 x then Just x else acc) Nothing tunels

convertLink :: Maybe Link -> Link
convertLink (Just link) = link
conevertLink Nothing = error "No existe el link entre las ciudades"

convertTunel :: Maybe Tunel -> Tunel
convertTunel (Just tunel) = tunel
convertTunel Nothing = error "No existe el tunel entre las ciudades"

usedCapacity :: Region -> Link -> Int
usedCapacity region@(Reg _ _ tunelList) link = foldr (+) 0 [1 | tunel <- tunelList, usesT link tunel]

nameInRegion :: City -> Region -> Bool
nameInRegion city (Reg cityList _ _) = elem (nameC city) [nameC n| n <- cityList]

pointInRegion :: City -> Region -> Bool
pointInRegion city (Reg cityList _ _)  = elem 0 [distanceC city n| n <- cityList] 