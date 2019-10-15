import sys
from java.lang import StringBuffer, System


sb = StringBuffer(100)    # Preallocate StringBuffer size for performance.

sb.append('The platform is: ')
sb.append(sys.platform)  # Python property
sb.append(' time for an omelette.')

sb.append('\n')     # Newline
sb.append('Home directory: ')
sb.append( System.getProperty('user.home') )

sb.append('\n')     # Newline
sb.append('Some numbers: ')
sb.append(44.1)
sb.append(', ')
sb.append(42)
sb.append(' ')

# Try appending a tuple.
tup=( 'Red', 'Green', 'Blue', 255, 204, 127 )
sb.append(tup)

print(sb.toString())


# Treat java.util.Properties as Python dictionary.
props = System.getProperties()

print('User home directory:', props['user.home'])
