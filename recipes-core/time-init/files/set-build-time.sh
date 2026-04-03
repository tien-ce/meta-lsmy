#!/bin/sh

BUILD_TIME="@BUILD_TIME@"

echo "Setting system time to build time: $BUILD_TIME"

date -s "$BUILD_TIME"