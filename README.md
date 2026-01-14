meta-coreiot-labsafety
1. Overview

meta-coreiot-labsafety is a custom Yocto Project meta-layer developed for an AI-integrated IoT system for laboratory environment and safety monitoring, based on the CoreIoT platform.

This layer is part of an academic and practical project that focuses on building a secure, scalable, and optimized embedded Linux system capable of:

Collecting environmental data from industrial sensors (Modbus RS-485)

Running AI inference at the edge using camera input

Sending data and alerts to an IoT platform for visualization and analysis

2. Project Objectives

The main objectives of this meta-layer are:

Customize and optimize embedded Linux using Yocto Project

Integrate industrial sensors (temperature, humidity, CO₂, VOC) via Modbus RS-485

Support AI-based camera applications (people counting, fatigue/inattention detection)

Provide system-level configurations for security, stability, and performance

Enable seamless integration with the CoreIoT platform

3. System Architecture (High-Level)
Industrial Sensors (RS-485 / Modbus)
            |
            v
     Embedded Linux Device
     (Yocto-based Image)
            |
   +--------------------+
   | AI Inference (Edge)|
   | Camera Processing |
   +--------------------+
            |
            v
        CoreIoT Platform
   (Dashboard, Alerts, History)