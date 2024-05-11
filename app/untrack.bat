@echo off
for /F "tokens=*" %%A in (.gitignore) do (
    git rm --cached -r %%A
)
git add .
git commit -m "Untrack files listed in .gitignore"
git push origin main