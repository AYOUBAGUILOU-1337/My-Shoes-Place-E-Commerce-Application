@echo off
REM publish_to_github.bat
REM Usage: publish_to_github.bat <your-github-repo-url> [branch]
REM Example: publish_to_github.bat https://github.com/yourname/ShoesPlace.git main

IF "%1"=="" (
    echo Please pass the GitHub repository url as an argument.
    echo Example: publish_to_github.bat https://github.com/yourname/ShoesPlace.git main
    exit /b 1
)

SET REPO_URL=%1
SET BRANCH=%2
IF "%BRANCH%"=="" SET BRANCH=main

REM Initialize git if not present
IF NOT EXIST .git (
    echo Initializing git repository...
    git init
) ELSE (
    echo Git repository already exists...
)

REM Add all files, commit
git add .
git commit -m "chore: initial commit - ShoesPlace Android scaffold" || echo "No changes to commit"

REM Add remote if it's not set
for /f "tokens=2 delims==" %%a in ('git remote -v ^| findstr /i "origin"') do (
    set REMEX=%%a
)
IF NOT DEFINED REMEX (
    git remote add origin %REPO_URL%
) ELSE (
    echo "origin remote already set"
)

REM Push to remote
git branch -M %BRANCH%

git push -u origin %BRANCH%

IF %ERRORLEVEL% NEQ 0 (
    echo Push failed. Make sure you have permission to push to the repository and credentials are set.
    echo Consider using GitHub CLI 'gh auth login' or setting up an access token to use via HTTPS.
) ELSE (
    echo Push successful!
)

pause
