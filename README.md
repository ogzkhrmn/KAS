# Endpointler

Swagger dökümanına ulaşmak için /document.html linki kullanılmalıdır.

### Yeni konferans eklenmesi

    /conferences -- POST

Yukarıda ki endpoint ile sisteme yeni konferans eklenir. Eklemek için aşağıdaki biçimde request gönderilmelidir.

    {
        "name": "Conference 1",
        "startDate": "2022-08-16",
        "address": "Istanbul, Turkey",
        "description": "An example conference",
        "speakers": [
            {
            "name": "Jeff",
            "photoUrl": "https://ww.google.com",
            "topics": "Java, Spring"
            }
        ]
    }

**speakers** alanı konuşmacıları listelemektedir.

### Mevcut Konferansların Listelenmesi

    /conferences -- GET

Yukarıda ki endpoint ile sistemdeki tarihi bugünün tarihinden ileri olan konferanslar listelenir.

    [
        {
            "id": 1,
            "name": "Java Day",
            "startDate": "2022-08-01",
            "address": "Istanbul",
            "description": "Best Java Conference",
            "speakers": [
                {
                "name": "Jeff Bezos",
                "photoUrl": "https://www.google.com",
                "topics": "Java, Spring"
                }
            ],
            "tickets": [
                {
                "name": "Jeff",
                "price": 375,
                "ticketType": "EARLY_BIRD"
                }
            ]
        }
    ]

**tickets** alanı satın alınan bilet ve bilete ait bilgileri listelemektedir.

### Özel bir Konferansın Bilgilerini Getirme

    /conferences/{id} -- GET

Yukarıda ki endpoint ile özel bir konferansa ait bilgiler getirilebilir.

    {
        "id": 1,
        "name": "Java Day",
        "startDate": "2022-08-01",
        "address": "Istanbul",
        "description": "Best Java Conference",
        "speakers": [
            {
            "name": "Jeff Bezos",
            "photoUrl": "https://www.google.com",
            "topics": "Java, Spring"
            }
        ],
        "tickets": [
            {
            "name": "Jeff",
            "price": 375,
            "ticketType": "EARLY_BIRD"
            }
        ]
    }
    
**tickets** alanı satın alınan bilet ve bilete ait bilgileri listelemektedir.

### İndirim Bilgielerini Getirme

    tickets/discounts -- POST

Yukarıda ki endpoint ile mevcut bilet dönemimne ait bilet için indirimli fiyat bilgileri getirilir.
Eğer gönderilen indirim kodu hatalı ise sistem bir hata mesajı fırlatır.
Aşağıdaki gibi bir indirim kodu gönderilirse sistem bu indirim kodunu kullanarak indirimli fiyatı getirir.

    {
        "discountCode": "KORVOPREMIUM"
    }

Eğer işlem başarılı olursa aşağıdaki gibi cevap döner.

    {
        "discountCode": "KORVOPREMIUM",
        "discount": 25,
        "price": 375
    }

### Bilet Satın Alma

    tickets -- POST

Yukarıda ki endpoint ile bilet satın alınabilir.
Aşağıdaki gibi body ile gönderilmelidir.

    {
        "name": "Jeff",
        "conferenceId": 1,
        "discountCode": "KORVOPREMIUM"
    }

**discountCode** alanı kullanıcının kullandığı indirim kodunu temsil eder.
Boş gönderilebilir. İndirim kodu bulunamaz ise sistem hata alır.

Eğer işlem başarılı olursa aşağıdaki gibi cevap döner.

    {
        "name": "Jeff",
        "price": 375,
        "ticketType": "EARLY_BIRD"
    }

## Uygulamanın Çalıştırılması

Öncelikle docker image oluşturmak için aşağıdaki komut çalıştırılmalıdır.

    docker build -t korvo .

Docker image oluşturulduktan sonra aşağıdaki komut çalıştırılmalıdır.

    docker run -dp 8080:8080 korvo