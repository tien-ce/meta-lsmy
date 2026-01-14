# Custom Yocto Project meta-layer developed for LSMY

## 1. Overview

`meta-lsmy` is a custom Yocto Project meta-layer developed for an **AI-integrated IoT system for laboratory environment and safety monitoring**, based on the **CoreIoT platform**.

This layer is part of an academic and practical project that focuses on building a **secure, scalable, and optimized embedded Linux system** capable of:

- Collecting environmental data from industrial sensors (Modbus RS-485)
- Running AI inference at the edge using camera input
- Sending data and alerts to an IoT platform for visualization and analysis

## 2. Project Objectives

The main objectives of this meta-layer are:

- Customize and optimize embedded Linux using **Yocto Project**
- Integrate **industrial sensors** (temperature, humidity, CO₂, VOC) via Modbus RS-485
- Support **AI-based camera applications** (people counting, fatigue/inattention detection)
- Provide system-level configurations for **security, stability, and performance**
- Enable seamless integration with the **CoreIoT platform**

## 3. System Architecture (High-Level)

```
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
```

## 4. Layer Features

This meta-layer provides:

- Custom image recipes for IoT + AI workloads
- Modbus RS-485 support (libmodbus, drivers, utilities)
- AI runtime support (TensorFlow Lite / ONNX Runtime)
- Camera and multimedia support (V4L2, GStreamer – optional)
- Security and system hardening configurations
- Performance and power optimization settings

## 5. Layer Structure

```
meta-lsmy/
├── conf/
|    └── layer.conf
|	 ├── distro/
|    └── machine/
|        └── raspberrypi4-lsmy.conf
|
└── recipes-core/
|    └── images/
|    |   └── lsmy-image.bb
|    └── packagegroups/
|        └── packagegroup-lsmy-base.bb
|        └── packagegroup-lsmy-network.bb
|        └── packagegroup-lsmy-ai.bb
└── COPYING.MIT
└── README.md
```

## 6. Supported Yocto Version

This layer is tested with:

- **Yocto Project Release:** `kirkstone`
- **BitBake:** compatible with Kirkstone branch
- **Target:** Embedded Linux devices (ARM-based platforms)

## 7. Dependencies

This layer depends on the following Yocto layers:

```
meta
meta-poky
meta-openembedded
meta-oe
meta-networking
meta-multimedia (optional)
```

## 8. How to Use

### 8.1 Add Layer to Build

```bash
cd poky
source oe-init-build-env
bitbake-layers add-layer ../meta-lsmy
```

### 8.2 Build Image

```bash
bitbake lsmy-image
```

## 9. Use Cases

- Laboratory environment monitoring
- Safety supervision in research labs
- Edge AI deployment for smart buildings
- Academic research on Yocto + AI + IoT systems

## 10. Future Extensions

Planned enhancements include:

- Advanced AI features (mask detection, overcrowding detection)
- Secure OTA updates
- Hardware acceleration (NPU / GPU)
- Enhanced power management for edge devices

## 11. Academic Context

This meta-layer is developed as part of the project:

**"Development of an IoT System with AI and Yocto Linux for Laboratory Environment and Safety Monitoring Based on CoreIoT Platform"**

The repository serves both **research and practical implementation purposes**.

## 12. References

- Yocto Project Documentation: https://www.yoctoproject.org/docs
- Modbus Application Protocol Specification
- TensorFlow Lite for Edge Devices
- ONNX Runtime
- CoreIoT Platform Documentation

## 13. License

This project is intended for **educational and research purposes**.