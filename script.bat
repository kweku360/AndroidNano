@echo off

for /F "tokens=2" %%i in ('date /t') do set mydate=%%i
set mytime=%time%
echo Current time is %mydate%:%mytime%

git add --all
git commit -m'Backup-Commit=%mydate%:%mytime%'
git push origin
pause
