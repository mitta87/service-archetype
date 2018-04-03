import org.apache.maven.shared.invoker.DefaultInvocationRequest
import org.apache.maven.shared.invoker.DefaultInvoker

import java.nio.file.Paths

println "Generating child service module from archetype"

def archetypeGroupId = request.getArchetypeGroupId()
def archetypeArtifactId = request.getArchetypeArtifactId()
def archetypeVersion = request.getArchetypeVersion()
def groupId = request.getGroupId()
def artifactId = "new-${request.getArtifactId()}".toString()
def version = request.getVersion()
def serviceName = "new-${request.getProperties().getProperty('serviceName')}".toString()
def packageName = "${request.getPackage()}.service".toString()

def artifactGenerationProps = request.getProperties()

println artifactGenerationProps

def props = [
        archetypeGroupId: archetypeGroupId,
        archetypeArtifactId: archetypeArtifactId,
        archetypeVersion: archetypeVersion,
        groupId: groupId,
        artifactId: artifactId,
        version: version,
        package: packageName,
        serviceName: serviceName
]

def invocationProperties = new Properties()
invocationProperties.putAll(props)

def invoker = new DefaultInvoker()
def request = new DefaultInvocationRequest();
request.setGoals(["archetype:generate"])
request.setProperties(invocationProperties)
request.setBaseDirectory(Paths.get("generated-service-aggregator").toFile())

def invocationResult = invoker.execute(request)

if (invocationResult.getExitCode() != 0) {
    println "Failed to generate service"
    if (invocationResult.getExecutionException() != null) {
        println "Exception: ${invocationResult.getExecutionException()}"
    }
} else {
    println "Service successfully generated"
}



