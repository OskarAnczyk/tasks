call runcrud
if "%ERRORLEVEL%" == "0" goto openchrome
echo.
echo RUNCRUD has errors
goto fail

:openchrome
"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe"
if "%ERRORLEVEL%" == "0" goto gettasks
echo.
echo Cannot open chrome
goto fail

:getTasks
"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" --kiosk "http://localhost:8080/crud/v1/task/getTasks"
if %ERRORLEVEL%" == "0" goto end
echo.
echo Cannot open site
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.