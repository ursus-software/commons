#!/usr/bin/env bash

TRAVIS="${TRAVIS:-""}"
TRAVIS_PULL_REQUEST="${TRAVIS_PULL_REQUEST:-"false"}"
GITHUB_USER="${GITHUB_USER:-""}"
GITHUB_TOKEN="${GITHUB_TOKEN:-""}"
COMMIT_AUTHOR_EMAIL="${COMMIT_AUTHOR_EMAIL:-"$(git config user.email)"}"

if [ "${TRAVIS_PULL_REQUEST}" = "false" ]; then
    mvn release:prepare && mvn release:perform
    if [ "$?" -ne "0" ]; then
        if [ "${TRAVIS}" ==  "true" ]; then
            git remote rm origin
            if [ "${GITHUB_TOKEN}" != "" ]; then
                if [ "${GITHUB_USER}" != "" ]; then
                    git remote add origin https://"${GITHUB_USER}":"${GITHUB_TOKEN}"@github.com/ursus-software/commons.git
                else
                    git remote add origin https://"${GITHUB_TOKEN}"@github.com/ursus-software/commons.git
                fi
            fi
        fi
        if [[ $- == *i* ]]; then
            read -p "Are you sure? " -n 1 -r
            echo    # (optional) move to a new line
            if [[ ! $REPLY =~ ^[Yy]$ ]]; then
                exit 0
            fi
        fi
        git config user.name "Travis CI"
        git config user.email "$COMMIT_AUTHOR_EMAIL"
        git push origin
    fi
fi
