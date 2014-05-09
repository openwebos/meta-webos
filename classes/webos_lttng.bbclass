# Copyright (c) 2013 LG Electronics, Inc.
#
# webos_lttng
#
# This class is to be inherited by the recipe for any component that
# uses LTTng tracing.
#
# Each recipe is responsible for setting a compilation flag to enable
# its own LTTng tracepoints based on the value of WEBOS_LTTNG_ENABLED.

# LTTng is disabled by default. To enable, add:
#    WEBOS_LTTNG_ENABLED = "1"
# to your webos-local.conf or the location of your choice.
WEBOS_LTTNG_ENABLED ?= "0"

DEPENDS += "${@base_contains('WEBOS_LTTNG_ENABLED', '1', 'lttng-ust', '', d)}"
RDEPENDS_${PN} += "${@base_contains('WEBOS_LTTNG_ENABLED', '1', 'lttng-tools lttng-modules babeltrace', '', d)}"
