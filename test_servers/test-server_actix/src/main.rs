use actix_web::{get, Responder, HttpServer, App, post};

#[get("/")]
async fn hello_world() -> impl Responder{
    "Hello World"
}

#[post("/test_post")]
async fn test_post(req_body : String) -> impl Responder{
    format!("getted body : {} " , req_body)
}

#[actix_web::main]
async fn main() -> std::io::Result<()> {
    HttpServer::new(|| {
        App::new()
            .service(hello_world)
            .service(test_post)
    })
        .bind(("localhost", 8082))?
        .run()
        .await
}
