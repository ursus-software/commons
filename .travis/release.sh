#!/usr/bin/env bash

TRAVIS="${TRAVIS:-""}"
TRAVIS_PULL_REQUEST="${TRAVIS_PULL_REQUEST:-"false"}"
GITHUB_USER="${GITHUB_USER:-""}"
GITHUB_TOKEN="${GITHUB_TOKEN:-""}"

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
        git push origin
    fi
fi
