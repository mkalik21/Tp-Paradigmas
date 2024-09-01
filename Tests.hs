import Anuncio
import FileSystem
import Tipos
import Prompter
import Control.Exception
import System.IO.Unsafe


testF :: Show a => a -> Bool
testF action = unsafePerformIO $ do
    result <- tryJust isException (evaluate action)
    return $ case result of
        Left _ -> True
        Right _ -> False
    where
        isException :: SomeException -> Maybe ()
        isException _ = Just ()


-- Datos de prueba
nombre1 = "Anuncio1"
nombre2 = "Anuncio2"
nombre3 = "Anuncio3"
nombre4 = "Anuncio4"
nombreVacio = ""
departamento1 = "Ventas"
departamento2 = "Marketing"
departamento3 = "IT"
departamentoVacio = ""
duracion1 = 30
duracion2 = 45
duracion3 = 60
duracion4 = 90
duracionNegativa = -10

anuncio1 = agregarA departamento1 (nuevoA nombre1 duracion1)
anuncio2 = agregarA departamento2 (nuevoA nombre2 duracion2)
anuncioConAmbosDpts = agregarA departamento1 (agregarA departamento2 (nuevoA nombre3 duracion3))
anuncioSinDptos = nuevoA nombre4 duracion4

fsVacio = nuevoF
fsConDpto = agregarDepartamentoF departamento1 fsVacio
fsConAnuncio = agregarAnuncioF anuncio1 fsConDpto
fsConAmbosDptos = agregarAnuncioF anuncioConAmbosDpts fsConDpto

prompterVacio = nuevoP fsVacio
prompterConAnuncio = configurarP (nuevoP fsConAnuncio) [departamento1]

testShowP :: Prompter -> Bool
testShowP prompter =
  let result = [ testF (showP prompter)
              , testF (showP (avanzarP prompter)) -- Avanzar debería ser seguro cuando se llega al final
              , testF (showP (configurarP prompter [])) -- Configurar con lista vacía
              ]
  in and result

-- Test cases
t = [
    -- Pruebas para Anuncio

      -- Pruebas para nuevoA
      testF (nuevoA "" duracion1),           -- Nombre vacío no debería ser válido
      testF (nuevoA nombre1 (-10)),          -- Duración negativa no debería ser válida
      nombreA anuncio1 == nombre1,           -- Verificar que el nombre del anuncio es correcto
      duracionA anuncio1 == duracion1,       -- Verificar que la duración del anuncio es correcta

      -- Pruebas para agregarA
      departamentosA (agregarA departamento1 anuncio1) == [departamento1], -- Departamento agregado correctamente
      departamentosA (agregarA departamento1 anuncio1) == [departamento1], -- No se deben agregar departamentos duplicados

     -- Pruebas para sacarA
      -- Si tiene varios departamentos, que no se elimine ninguno que no pedimos sacar
      departamentosA (sacarA departamento2 anuncioConAmbosDpts) == [departamento1],  -- Solo se elimina el departamento especificado
      -- Si tiene un solo departamento, que se elimine al sacarlo
      null (departamentosA (sacarA departamento1 anuncio1)),  -- Si eliminamos el único departamento, debe quedar vacío

      -- Pruebas para nombreA
      nombreA anuncio1 == nombre1,  -- Verificar que el anuncio tiene el nombre correcto
      nombreA anuncio2 == nombre2,  -- Verificar que el segundo anuncio tiene el nombre correcto

      -- Pruebas para departamentosA
      departamentosA anuncio1 == [departamento1],  -- Verificar que el anuncio tiene el departamento correcto
      departamentosA anuncio2 == [departamento2],  -- Verificar que el anuncio tiene el departamento correcto
      departamentosA anuncioConAmbosDpts == [departamento1, departamento2],  -- Verificar que el anuncio tiene los dos departamentos

      -- Pruebas para duracionA
      duracionA anuncio1 == duracion1,  -- Verificar la duración del primer anuncio
      duracionA anuncio2 == duracion2,  -- Verificar la duración del segundo anuncio

      -- Pruebas para aplicaA
      aplicaA [departamento1] anuncio1,  -- Debe aplicar para el departamento1
      not (aplicaA [departamento2] anuncio1),  -- No debe aplicar para el departamento2 si no está en la lista


      --Pruebas para FileSystem

      -- Pruebas para nuevoF
      null (departamentosF fsVacio),         -- Verificar que el FileSystem vacío no tiene departamentos
      null (anunciosF fsVacio),              -- Verificar que el FileSystem vacío no tiene anuncios

      -- Pruebas para departamentosF
      departamentosF fsConDpto == [departamento1],  -- Verificar que el departamento se agregó correctamente

      -- Pruebas para anunciosF
      anunciosF fsConAnuncio == [anuncio1],  -- Verificar que el anuncio se agregó correctamente

      -- Pruebas para agregarDepartamentoF
      departamentosF fsConDpto == [departamento1],  -- Verificar que el departamento se agregó correctamente
      departamentosF (agregarDepartamentoF departamento1 fsConDpto) == [departamento1],  -- No debe duplicar departamentos

      -- Pruebas para agregarAnuncioF
      anunciosF fsConAnuncio == [anuncio1],  -- Verificar que el anuncio se agregó correctamente
      anunciosF (agregarAnuncioF anuncio2 fsConAnuncio) == [anuncio1],  -- No agrega otro anuncio porque no tiene el departamento de anuncio 2.
      anunciosF (agregarAnuncioF anuncioConAmbosDpts fsConAnuncio) == [anuncioConAmbosDpts, anuncio1],  -- Verificar que el anuncio se agregó correctamente
      null (anunciosF (agregarAnuncioF anuncio2 fsVacio)),  -- No debe agregar anuncio si los departamentos no existen

      -- Pruebas para sacarDepartamentoF
      null (departamentosF (sacarDepartamentoF departamento1 fsConDpto)),  -- Verificar que el departamento se eliminó
      departamentosF (sacarDepartamentoF departamento2 fsConDpto) == [departamento1],  -- No debe eliminar si el departamento no existe

      -- Pruebas para sacarAnuncioF
      null (anunciosF (sacarAnuncioF anuncio1 fsConAnuncio)),  -- Verificar que el anuncio se eliminó
      anunciosF (sacarAnuncioF anuncio2 fsConAnuncio) == [anuncio1],  -- No debe eliminar si el anuncio no existe

      -- Pruebas para anunciosParaF
      anunciosParaF [departamento1] fsConAnuncio == [anuncio1],  -- Verificar que devuelve el anuncio correcto para un departamento
      null (anunciosParaF [departamento2] fsConAnuncio),  -- Verificar que no devuelve anuncios para departamentos no existentes
      anunciosParaF [departamento1, departamento2] fsConAnuncio == [anuncio1],  -- Verificar con varios departamentos

      -- Pruebas para nuevoP
      archivosP (nuevoP fsConDpto) == fsConDpto,                -- Verificar que el FileSystem se inicializa correctamente
      departamentosP (nuevoP fsConDpto) == [departamento1],     -- Verificar que los departamentos se inicializan correctamente

      -- Pruebas para archivosP
      archivosP prompterVacio == fsVacio,                       -- Verificar que se retorna el FileSystem correcto
      archivosP prompterConAnuncio == fsConAnuncio,             -- Verificar que se retorna el FileSystem correcto con anuncios

      -- Pruebas para departamentosP
      null (departamentosP prompterVacio),                       -- Verificar que se retorna la lista de departamentos vacía
      departamentosP prompterConAnuncio == [departamento1],      -- Verificar que se retorna la lista de departamentos correcta

      -- Pruebas para configurarP
      archivosP prompterConAnuncio == fsConAnuncio,             -- Verificar que el FileSystem se actualiza correctamente
      length (anunciosF (archivosP prompterConAnuncio)) == 1,   -- Verificar que el número de anuncios es correcto
   
      -- Pruebas para anunciosP
      anunciosP prompterConAnuncio == [nombre1],                -- Verificar que se retorna la lista correcta de nombres de anuncios

      -- Pruebas para showP
      testShowP prompterConAnuncio,                             -- Verificar que se muestra el anuncio correcto

      -- Pruebas para avanzarP
      avanzarP prompterConAnuncio == prompterConAnuncio,        -- Verificar que avanzarP no cambia el estado si hay un solo anuncio
      avanzarP prompterConAnuncio == prompterConAnuncio,        -- Este test debería pasar, ya que no hay más anuncios a los que avanzar


      -- Pruebas para duracionP
      duracionP prompterConAnuncio == duracion1,                -- Verificar que se calcula la duración total correctamente
      duracionP (configurarP (nuevoP fsVacio) []) == 0          -- Verificar que la duración es 0 para un prompter vacío
    ]
