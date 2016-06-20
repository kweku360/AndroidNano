@echo off
for /F "tokens=2" %%i in ('date /t') do set mydate=%%i
set mytime=%time%
git add --all
git commit -m'Backup-Commit=%mydate%:%mytime%'
git push origin > gitlogs.txt
echo Backup successfull on %mydate%:%mytime% >> gitlogs.txt
echo . >> gitlogs.txt
pause
