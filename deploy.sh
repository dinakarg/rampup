#!/bin/bash

mvn -e clean install -Dmaven.test.skip=true -Pdeploy -Dcq.port=4502 -Dcq.host=127.0.0.1