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
foundR (Reg cities links tunels) city | not (elem city cities) = Reg (cities ++ [city]) links tunels
                                       | otherwise = error "La ciudad ya se encuentra en la región"

linkR :: Region -> City -> City -> Quality -> Region -- enlaza dos ciudades de la región con un enlace de la calidad indicada
linkR (Reg cities links tunels) c1 c2 qua = Reg cities (links ++ [newL c1 c2 qua]) tunels -- ver que pasa si ya existe el link

checkCities :: Region -> [City] -> Bool
checkCities (Reg regCities _ _) cityList = isInfixOf cityList regCities

tunelR :: Region -> [ City ] -> Region -- genera una comunicación entre dos ciudades distintas de la región
tunelR (Reg cities links tunels) cityList | 

{ la lista de ciudades indica el camino ordenado de enlaces que debe tomar el túnel de inicio  a fin }
{ Hay decisiones que tomar! }

connectedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades están conectadas por un túnel
connectedR (Reg _ _ tunels) c1 c2 = foldr (\x acc -> connectsT c1 c2 x || acc) False tunels
{- Dice si existe un túnel en la región que tenga estas ciudades por origen o destino -}


linkedR :: Region -> City -> City -> Bool -- indica si estas dos ciudades están enlazadas
linkedR (Reg _ links _) = foldr (\x acc -> linksL c1 c2 x || acc) False links
{- Dice si existe un enlace en la región que entre estas dos ciudades -}

delayR :: Region -> City -> City -> Float -- dadas dos ciudades conectadas, indica la demora
{ Hay decisiones que tomar! }
availableCapacityForR :: Region -> City -> City -> Int -- indica la capacidad disponible entre dos ciudades
{ Teniendo en cuenta la capacidad que los túneles existentes ocupan }