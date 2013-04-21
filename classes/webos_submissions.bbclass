# Copyright (c) 2012 Hewlett-Packard Development Company, L.P.
#
# webos_submissions
#
# Supply the "enhanced submissions" interface so that either can be used by
# recipes. Not applicable to Open webOS where all components have their 
# submissions appended to PV. Instead, explicitly inheriting from this bbclass
# implies that the component does not use the Open webOS convention for
# submission tags, i.e., they are not of the form:
#    submissions/<integer>
#       or
#    submissions/<integer>.<integer>
#

inherit webos_enhanced_submissions

