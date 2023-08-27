module Link ( Link, newL, linksL, connectsL, capacityL, delayL)
   where

import Point
import City
import Quality

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link 
newL city1 city2 quality | city1 /= city2 = Lin city1 city2 quality
                         | otherwise = error "las ciudades deben ser distintas"

connectsL :: City -> Link -> Bool   
connectsL city (Lin c1 c2 _) = city == c1 || city == c2

linksL :: City -> City -> Link -> Bool 
linksL city1 city2 (Lin c1 c2 _) = (city1 == c1 && city2 == c2) || (city1 == c2 && city2 == c1)

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality

delayL :: Link -> Float    
delayL (Lin city1 city2 quality) = delayQ quality * distanceC city1 city2