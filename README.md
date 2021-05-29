- istioctl install --set profile=demo -y
- kubectl label namespace default istio-injection=enabled
- skaffold run (from project root)
- kubectl apply -f service-mesh/addons
````
While ($true) { curl -UseBasicParsing http://127.0.0.1/customers; Start-Sleep -Seconds 1;}
````

- istioctl dashboard grafana

### calling example:
- https://github.com/piomin/sample-istio-services