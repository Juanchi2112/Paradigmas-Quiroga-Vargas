module Link ( Link, newL, linksL, connectsL, capacityL, delayL, tupleLinks, notSharedCity )
   where

import City
import Quality

data Link = Lin City City Quality deriving (Eq, Show)

newL :: City -> City -> Quality -> Link -- genera un link entre dos ciudades distintas
newL city1 city2 quality = Lin city1 city2 quality

sharedCity :: Link -> Link -> City
sharedCity (Lin c1 c2 _) link2 | connectsL c1 link2 = c1
                               | connectsL c2 link2 = c2

connectsL :: City -> Link -> Bool   -- indica si esta ciudad es parte de este link
connectsL city (Lin c1 c2 _) = city == c1 || city == c2

linksL :: City -> City -> Link -> Bool -- indica si estas dos ciudades distintas estan conectadas mediante este link
linksL city1 city2 (Lin c1 c2 _) = (city1 == c1 && city2 == c2) || (city1 == c2 && city2 == c1)

capacityL :: Link -> Int
capacityL (Lin _ _ quality) = capacityQ quality

delayL :: Link -> Float     -- la demora que sufre una conexion en este canal
delayL (Lin _ _ quality) = delayQ quality

notSharedCity :: Link -> Link -> City
notSharedCity (Lin c1 c2 _) link2 | connectsL c1 link2 = c2
                                 | connectsL c2 link2 = c1


