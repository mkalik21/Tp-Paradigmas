module Anuncio ( Anuncio, nuevoA, nombreA, duracionA, departamentosA, agregarA, sacarA, aplicaA )
  where

import Tipos

data Anuncio = Anu Nombre [Departamento] Duracion deriving (Eq, Show, Ord)

nuevoA :: Nombre -> Duracion -> Anuncio         -- dado un nombre y una duracion en segundos retorna un nuevo Anuncio
nombreA :: Anuncio -> Nombre                    -- dado un anuncio retorna su nombre
duracionA :: Anuncio -> Duracion                -- dado un anuncio retorna su duración
departamentosA :: Anuncio -> [ Departamento ]   -- dado un anuncio retorna los departamentos que le fueron asociados
agregarA :: Departamento -> Anuncio -> Anuncio  -- permite asignar un departamento a un anuncio
sacarA :: Departamento -> Anuncio -> Anuncio    -- permite quitarle un departamento a un anuncio
aplicaA :: [ Departamento ] -> Anuncio -> Bool  -- responde si un anuncio debe emitirse para alguno de los departamentos consultados


nuevoA nombre duracion
    | null nombre = error "El nombre no puede ser una cadena vacía."
    | duracion < 0 = error "La duración no puede ser negativa."
    | otherwise = Anu nombre [] duracion

nombreA (Anu nombre _ _) = nombre

duracionA (Anu _ _ duracion) = duracion

departamentosA (Anu _ departamentos _) = departamentos

agregarA departamento (Anu nombre departamentos duracion) | departamento `elem` departamentos = Anu nombre departamentos duracion
                                                          | otherwise                         = Anu nombre (departamento : departamentos) duracion
sacarA departamento (Anu nombre departamentos duracion) = Anu nombre (filter (/=departamento) departamentos) duracion

aplicaA departamentosConsultados (Anu _ departamentosAnuncio _) = any (`elem` departamentosAnuncio) departamentosConsultados