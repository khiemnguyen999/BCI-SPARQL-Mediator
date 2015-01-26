#!/bin/sh

# This script is to deploy code to target folder
# Why need this script is due to the setting files
# => different machine will have different settings (especailly port setting)
# => but we don't want to mess the git folder

BASEDIR=`dirname $0`
tartget_dir="$HOME/WEB_API";

echo "== Decide Service folder =="
if [ $# -eq 1 ]; then
    tartget_dir = $1
    echo "-> use given path $tartget_dir"
else
    echo "-> use default path $tartget_dir"
fi


echo "== Sync to Service folder =="
cd $BASEDIR
if [ ! -d  $tartget_dir ]; then
    echo "create $tartget_dir"
    mkdir $tartget_dir
    rsync -av --exclude 'deploy.sh' . $tartget_dir
else
    if [ -d  "$tartget_dir/settings" ]; then
        rsync -av  --exclude 'deploy.sh' --exclude 'settings/' . $tartget_dir
    else
        rsync -av --exclude 'deploy.sh' . $tartget_dir
    fi
fi

echo "== Add Node Plugins =="
cd $tartget_dir



if [ -d "node_modules" ]; then
    echo "-> this script only knows you have added some plugins"
    echo "-> you need to check whether these fit the need from service or not"
else
    echo "-> auto add neccesary node plugins"
    npm install express
    npm install body-parser
fi


